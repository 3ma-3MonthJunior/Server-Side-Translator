public class Main {
    public static void main (String[] args) {

        ElasticFiller ef = new ElasticFiller();


        System.out.println(ef.getArrayListFromJson().size());

        System.out.println(ef.gettterArrayListFromJson().get(994).getId());
        System.out.println(ef.gettterArrayListFromJson().get(995).getGender());
        System.out.println(ef.gettterArrayListFromJson().get(996).getFirst_name());
        System.out.println(ef.gettterListFromJson().get(997).getLast_name());
        System.out.println(ef.gettterArrayListFromJson().get(998).getEmail());
        System.out.println(ef.gettterArrayListFromJson().get(999).getIp_address());
    }
}