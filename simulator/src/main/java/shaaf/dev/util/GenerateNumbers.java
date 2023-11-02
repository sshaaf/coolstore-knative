package shaaf.dev.util;

import java.text.DecimalFormat;
import java.util.Random;

public class GenerateNumbers {


    private final static int[] nationalAreaCode = {720, 303, 910, 414, 859, 202};
    private final static int[] ncAreaCode = {704, 828, 651, 336, 252, 984};

    private final static int[] ccRandoms = {4704, 4828, 4651, 4336, 4252, 4984};

    private final static int prefixCode = 555;

    private final static DecimalFormat fourDigits = new DecimalFormat("0000");
    private final static Random random = new Random();

    private final static String format = "(%s) %s-%s";

    private final static String ccFormat = "%s%s%s%s";

    private final static String orderFormat = "order-%s-id-0.%s";

    public static String getNextPhoneNumber() {
        int areaCode = 0;
        int percentage = random.nextInt(100);

        //Use the NC area codes for 90% of the time
        if (percentage < 90) {
            areaCode = ncAreaCode[random.nextInt(ncAreaCode.length)];
        } else {
            areaCode = nationalAreaCode[random.nextInt(nationalAreaCode.length)];
        }

        return String.format(format, areaCode, prefixCode, fourDigits.format(random.nextInt(9999)));
    }

    public static String getNextCreditCardNumber() {
        int startCode = 4235;
        return String.format(ccFormat, startCode, ccRandoms[random.nextInt(ccRandoms.length)], ccRandoms[random.nextInt(ccRandoms.length)],ccRandoms[random.nextInt(ccRandoms.length)]);
    }

    public static String getNextCvC(){
        return String.valueOf(ncAreaCode[random.nextInt(ncAreaCode.length)]);
    }

    public static String getNextMonth(){
        return String.valueOf(random.nextInt(12-1) + 1);
    }

    public static String getNextYear(){
        return String.valueOf(random.nextInt(29-24) + 24);
    }

    public static String getNextOrder(){
        return String.format(orderFormat, random.nextInt(999),getNextCreditCardNumber());
    }


}
