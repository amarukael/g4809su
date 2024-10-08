package helper;

import utility.Rand;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CaseHelper {

    Rand rand = new Rand();

    public void executeCaseFunction(String attribute, String sCase, String params, Object req) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> clazz = req.getClass();
        String attributeSetter = "set" + attribute;
        Method classSetter = clazz.getMethod(attributeSetter,String.class);

        Method[] methods = this.getClass().getMethods();

        Method method = null;
        for(Method m : methods){
            if(m.getName().equals(sCase)){
                method = m;
                break;
            }
        }

        Object value = "";
        if(method != null){
            CaseHelper caseHelper = new CaseHelper();
            if(!params.isEmpty()){
                Object[] dynamicParams = params.split(",");
                value = method.invoke(caseHelper,dynamicParams);
            }else{
                value = method.invoke(caseHelper);
            }
            classSetter.invoke(req,value);
        }
    }

    public String empty(){
        return "";
    }

    public String notInclude(){
        return null;
    }



    public String length(String max){
        int tempMax = Integer.parseInt(max);
        return rand.randStrAlfaNum(tempMax);
    }



}
