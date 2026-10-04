public class Main {

    public static void main(String[] args) {

        int passedTests = 0;

        Notification reminderEmail =
                new Reminder("R1", "Meeting at 10:00", new EmailChannel());

        Notification reminderSms =
                new Reminder("R1", "Meeting at 10:00", new SmsChannel());

        Notification urgentEmail =
                new UrgentAlert("U1", "Server is down", new EmailChannel());

        Notification urgentSms =
                new UrgentAlert("U1", "Server is down", new SmsChannel());


        if (runTest(
                "T1",
                "Reminder + EmailChannel",
                reminderEmail.execute(),
                "EMAIL: Reminder: Meeting at 10:00"
        )) {
            passedTests++;
        }


        if (runTest(
                "T2",
                "Reminder + SmsChannel",
                reminderSms.execute(),
                "SMS: Reminder: Meeting at 10:00"
        )) {
            passedTests++;
        }


        if (runTest(
                "T3",
                "UrgentAlert + EmailChannel",
                urgentEmail.execute(),
                "EMAIL: URGENT: Server is down"
        )) {
            passedTests++;
        }


        if (runTest(
                "T4",
                "UrgentAlert + SmsChannel",
                urgentSms.execute(),
                "SMS: URGENT: Server is down"
        )) {
            passedTests++;
        }



        Notification switchTest =
                new Reminder("R2", "Submit the assignment", new EmailChannel());

        Notification originalReference = switchTest;

        String before = switchTest.execute();

        String oldId = switchTest.getId();
        String oldMessage = switchTest.getMessage();

        switchTest.setImplementation(new SmsChannel());

        String after = switchTest.execute();

        boolean sameObject = originalReference == switchTest;

        boolean stateUnchanged =
                oldId.equals(switchTest.getId())
                        && oldMessage.equals(switchTest.getMessage());

        boolean t5Passed =
                sameObject
                        && stateUnchanged
                        && before.equals("EMAIL: Reminder: Submit the assignment")
                        && after.equals("SMS: Reminder: Submit the assignment");

        if (t5Passed) {
            passedTests++;

            System.out.println(
                    "T5 PASS | sameObject=" + sameObject
                            + " | stateUnchanged=" + stateUnchanged
            );

            System.out.println(
                    "before=" + before + " | after=" + after
            );
        } else {
            System.out.println(
                    "T5 FAIL | sameObject=" + sameObject
                            + " | stateUnchanged=" + stateUnchanged
            );

            System.out.println(
                    "before=" + before + " | after=" + after
            );
        }


        Notification reminderPush =
                new Reminder("R3", "Meeting at 10:00", new PushChannel());

        Notification urgentPush =
                new UrgentAlert("U2", "Server is down", new PushChannel());


        if (runTest(
                "T6",
                "Reminder + PushChannel",
                reminderPush.execute(),
                "PUSH: Reminder: Meeting at 10:00"
        )) {
            passedTests++;
        }


        if (runTest(
                "T7",
                "UrgentAlert + PushChannel",
                urgentPush.execute(),
                "PUSH: URGENT: Server is down"
        )) {
            passedTests++;
        }


        System.out.println();
        System.out.println("SUMMARY: " + passedTests + "/7 PASS");
    }


    private static boolean runTest(
            String testName,
            String classes,
            String actual,
            String expected
    ) {

        boolean passed = actual.equals(expected);

        if (passed) {
            System.out.println(
                    testName + " PASS | " + classes + " | result=" + actual
            );
        } else {
            System.out.println(
                    testName + " FAIL | " + classes
                            + " | actual=" + actual
                            + " | expected=" + expected
            );
        }

        return passed;
    }
}

