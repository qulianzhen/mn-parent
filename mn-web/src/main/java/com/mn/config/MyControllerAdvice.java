package com.mn.config;

import com.mn.commonbean.exception.BusinessException;
import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

/** 可以看作是Controller层增强处理，这里确实不是AOP的实现
 * @version 1.0
 * @desc 描述 : DispatcherServlet 内处理结果时，调用processDispatchResult方法，然后调用
 * processHandlerException()方法，这个方法内部会轮询processHandlerException，进行异常处理；
 * 在这些resolver中，有一个resolver专门捕获异常使用，那就是ExceptionHandlerExceptionResolver，
 * 处理异常时，会通过getExceptionHandlerMethod去获取异常处理方法，该方法中，会拿到定义的所有的ControllerAdvice，
 * 根据Order(序号越小，越优先处理),去获取匹配的异常处理方法 ，拿到之后，基于反射进行我们定义方法的调用，
 * 并拿到结果进行最终结果的返回; 排名靠前的advice类，找到匹配的异常处理method后，
 * 将不再继续执行查找过程，直接返回靠前的method
 * @auth qulianzhen
 * @date 2020-04-19 23:46
 */
//如果确定处理结果都是json，可以直接用 @RestControllerAdvice,如果想返回一些ModeAndView，则
//需要jason的单独写@ResponseBody
@ControllerAdvice
public class MyControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyControllerAdvice.class);
    /*此类通常用三大用途*/
    /*1.全局异常处理*/
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Message customException(Throwable e) {
        LOGGER.error("error:",e);
        return MessageUtil.errorMsg(Message.UNKNOWN_EXCEPTION_MSG,Message.UNKNOWN_EXCEPTION);
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Message customException(BusinessException e) {
        return MessageUtil.errorMsg(e.getMessage(),Message.BUSINESS_EXCEPTION);
    }

    @ResponseBody
    //@ResponseStatus(code = HttpStatus.BAD_REQUEST)//这个注解使返回的code成了400了
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public Message handleParameterVerificationException(Exception e) {
        LOGGER.error(" handleParameterVerificationException has been invoked", e);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("code", "100001");
        String msg = null;
        /// BindException
        if (e instanceof BindException) {
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = ((BindException) e).getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
            /// MethodArgumentNotValidException
        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
            /// ValidationException 的子类异常ConstraintViolationException
        } else if (e instanceof ConstraintViolationException) {
            /*
             * ConstraintViolationException的e.getMessage()形如
             *     {方法名}.{参数名}: {message}
             *  这里只需要取后面的message即可
             */
            msg = e.getMessage();
            if (msg != null) {
                int lastIndex = msg.lastIndexOf(':');
                if (lastIndex >= 0) {
                    msg = msg.substring(lastIndex + 1).trim();
                }
            }
            /// ValidationException 的其它子类异常
        } else {
            msg = "处理参数时异常";
        }
        return MessageUtil.errorMsg(msg,Message.INVALID_PARAM);
    }





    /*全局数据绑定:既可以自定义变量名，也可以直接用Model对象*/
    @ModelAttribute
    public void addAttributes(Model model) {
        //model.addAttribute("author", "Magical Sam");
        //public String home(ModelMap modelMap){System.out.println(modelMap.get("author"));}
    }

    /*@ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }*/

    /*全局数据预处理：比如book和author这两个bean中都有name属性，可以通过绑定前缀来区分
    * 即b.name指的是book中的，a.name指的是author中的*/
    /*@PostMapping("/book")
    public void addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
        System.out.println(book);
        System.out.println(author);
    }*/
    /*@InitBinder("b")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }
    @InitBinder("a")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }*/




}
