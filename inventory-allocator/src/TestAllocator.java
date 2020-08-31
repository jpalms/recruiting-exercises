public class TestAllocator {

    private final static String oneHouse1 = "{ apple: 1 }";
    private final static String oneHouse2 = "[{ name: owd, inventory: { apple: 1 } }]";
    private final static String oneHouseResult = "[{ owd: { apple: 1 } }]";

    private final static String multiHouse1 = "{ apple: 10 }";
    private final static String multiHouse2 = "[{ name: owd, inventory: { apple: 5 } }, { name: dm, inventory: { apple: 5 }}]";
    private final static String multiHouseResult = "[{ owd: { apple: 5 } }, { dm: { apple: 5 } }]";

    private final static String empty1 = "{ apple: 1 }";
    private final static String empty2 = "[{ name: owd, inventory: { apple: 0 } }]";
    private final static String emptyResult = "[]";

    private final static String fail1 = "{ apple: 2 }";
    private final static String fail2 = "[{ name: owd, inventory: { apple: 1 } }]";
    private final static String failResult = "[]";

    private final static String moreThan1 = "{ apple: 10 }";
    private final static String moreThan2 = "[{ name: owd, inventory: { apple: 11 } }, { name: dm, inventory: { apple: 5 }}]";
    private final static String moreThanResult = "[{ owd: { apple: 10 } }]";

    private final static String exact1 = "{ apple: 5 }";
    private final static String exact2 = "[{ name: owd, inventory: { apple: 5 } }, { name: dm, inventory: { apple: 5 }}]";
    private final static String exactResult = "[{ owd: { apple: 5 } }]";

    public static void main(String args[]){

        // can be shipped in one warehouse
        test(oneHouse1, oneHouse2, oneHouseResult);
        // shipped in multiple warehouses
        test(multiHouse1, multiHouse2, multiHouseResult);
        // not enough inventory to ship
        test(empty1, empty2, emptyResult);
        // not enough inventory to ship
        test(fail1, fail2, failResult);
        // morethan enough to ship
        test(moreThan1, moreThan2, moreThanResult);
        // excatly enought to get from one warehouse
        test(exact1, exact2, exactResult);
    }

    /**
     * Description: Tests to see if input gives expected output
     *
     * @param input1 - Items to find
     * @param input2 - Warehouses with inventory
     * @param result - Expected result
     */
    private static void test(String input1, String input2, String result){
        System.out.println("Line1: " + input1);
        System.out.println("Line2: " + input2 + "\n");
        String answer = Allocator.run(input1, input2);
        boolean match = answer.equals(result);

        if(match){
            System.out.println("Expected Output");
        } else{
            System.out.println("Wrong Output");
            System.out.println("Expected: " + result);
        }
        System.out.println("Result: " + answer + "\n");
    }
}
