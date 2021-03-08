import java.util.Map;

public class CheckAct {

    public void checkActOnMap(Map<String, String> customerMap){
        if(customerMap.containsKey("Darren_101")){
            String val = customerMap.remove("Darren_101");
            if(val == null){
                System.out.println("Value for key was null");
            }else{
                customerMap.put("Darren_101", "Dublin");
            }
        }
    }

    public static void main(String[] args) {

    }
}

//Map Explanation
//ID --> Address
