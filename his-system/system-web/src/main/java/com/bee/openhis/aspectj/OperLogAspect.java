package com.bee.openhis.aspectj;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.bee.openhis.aspectj.enums.BusinessStatus;
import com.bee.openhis.domain.OperLog;
import com.bee.openhis.domain.SimpleUser;
import com.bee.openhis.service.OperLogService;
import com.bee.openhis.utils.AddressUtils;
import com.bee.openhis.utils.IpUtils;
import com.bee.openhis.utils.ServletUtils;
import com.bee.openhis.utils.ShiroSecurityUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

@Component
@Aspect
@Log4j2
public class OperLogAspect {
    @Autowired
    private OperLogService operLogService; //保存日志的接口

    @Pointcut("@annotation(com.bee.openhis.aspectj.Log)")
    public void logPointCut() {
    }

    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }


    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }


    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {

            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }

            // 获取当前的用户
            SimpleUser loginUser = ShiroSecurityUtils.getCurrentSimpleUser();


            OperLog operLog = new OperLog();
            operLog.setStatus(String.valueOf(BusinessStatus.SUCCESS.ordinal()));

            // 请求的地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            operLog.setOperIp(ip);
            String address = AddressUtils.getRealAddressByIP(ip);
            operLog.setOperLocation(address);


            // 返回参数
            operLog.setJsonResult(JSON.toJSONString(jsonResult));
            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            operLog.setOperName(loginUser.getUserName());


            if (e != null) {
                operLog.setStatus(String.valueOf(BusinessStatus.FAIL.ordinal()));
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }


            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");

            // 设置请求方式
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operLog);
            //设置操作时间
            operLog.setOperTime(DateUtil.date());
            // 保存数据库
            operLogService.insertOperLog(operLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==后置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    private void getControllerMethodDescription(JoinPoint joinPoint, Log controllerLog, OperLog operLog) {
        operLog.setBusinessType(String.valueOf(controllerLog.businessType().ordinal()));
        operLog.setTitle(controllerLog.title());
        operLog.setOperatorType(controllerLog.operatorType().ordinal());
        if (controllerLog.isSaveRequestData()) {
            setRequestValue(joinPoint, operLog);
        }
    }

    private void setRequestValue(JoinPoint joinPoint, OperLog operLog) {
        String requestMethod = operLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setOperParam(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    private String argsArrayToString(Object[] args) {
        StringBuilder params = new StringBuilder();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (!isFilterObject(args[i])) {
                    String json = JSON.toJSONString(args[i]);
                    params.append(json).append(" ");
                }
            }
        }
        return params.toString().trim();
    }

    private boolean isFilterObject(Object arg) {
        return arg instanceof MultipartFile || arg instanceof HttpServletRequest || arg instanceof HttpServletResponse;
    }

    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        //只有父类对象本身就是用子类new出来的时候, 才可以在将来被强制转换为子类对象. 源码可见
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

}
