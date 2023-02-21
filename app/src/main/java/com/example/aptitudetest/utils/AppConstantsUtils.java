package com.example.aptitudetest.utils;

/**
 * Created by Muhammad Umer on 07/06/2020.
 */
public class AppConstantsUtils {


    public static final String REGISTER = "api/questions";

    public static final String POST_ANSWER = "api/store/quiz";
    public static final String JOB_ROLE = "api/job-role";


    /**
     * Created by Muhammad Ahmad on 07/07/2020.
     */
    public class ProfileUtils {

        static final String JOB_DETAIL = "job_detail";
        static final String LOGIN_AS_USER_DETAILS = "login_as_user_data";
        static final String LOGIN_AS_USER_ACTIVE = "login_as_user_active";
        static final String ACCOUNT_DETAIL = "account_detail";
        static final String LOAN_CATEGORIES = "loan_categories";
        static final String LOAN_CATEGORIES_ITEMS = "loan_categories_items";
        static final String PRODUCT_PURPOSE = "product_purpose";
        static final String CITY_LIST = "city_list";
        static final String BANK_LIST = "bank_list";
        static final String PROFESSION_LIST = "profession_list";

        public static final String EASY_PAISA = "Easy Paisa";
        public static final String JAZZ_CASH = "Jazz Cash";

        static final String GET_BORROWER_LIST = "get_borrower_list";
        static final String GET_USERPROFILE_LIST = "get_userprofile_list";

    }

    /**
     * Created by Muhammad Ahmad.
     */
    public class PreferenceUtils {

        static final String PREFERENCE_NAME = "apptitude_test";
        static final String ACCESS_TOKEN = "access_token";
        static final String PHONE_NUMBER = "phone_number";
        static final String USER_ID = "user_id";
        static final String CORR_ID = "corr_id";
        static final String PIN_CODE = "pin_code";
        static final String SESSION_INFO = "session_active";
        static final String DEVICE_ID = "device_id";
        static final String BORROWER_TYPE = "borrowertype";
        static final String LOGIN_CHECK = "loggedInmode";
        static final String APPLICATION_FEE = "is_fee_paid";
        static final String BIOMETRIC_LOGIN = "biometric_login";
        static final String REMAINING_BALANCE = "remaining_balance";

    }

    /**
     * Created by Muhammad Ahmad on 08/06/2020.
     */
    public class StageUtils {

        public static final String BASIC = "basic";
        public static final String BASIC_DOC = "basic_doc";
        public static final String EVALUATION = "evaluation";
        public static final String WALLET = "wallet";

        public static final String SURVEY = "survey";

        public static final String FEE = "fee";
        public static final String IN_PROCESS = "inProcess";
        public static final String FEE_CONFIRMATION = "fee_confirmation";
        public static final String FEE_CONFIRMED = "fee_confirmed";
        public static final String STATUS_EVALUATION = "status_evaluation";
        public static final String STATUS_NOT_EVALUATION = "status_not_evaluation";
        public static final String STATUS_LOANAPPLICATION = "status_loanapplication";
        public static final String STATUS_LOANAPPLICATIONR = "status_loanapplicationr";
        public static final String STATUS_LOANAPPLICATIONREJECTED = "status_loanapplicationrejected";

        public static final String STATUS_BANK = "status_bank";
        public static final String STATUS_REPAY = "status_repay";
        public static final String STATUS_NOT_LOANAPPLICATION = "status_not_loanapplication";

    }

    /**
     * Created by Muhammad Ahmad on 07/07/2020.
     */
    public class CameraCodeConstantUtils {


        public static final int UPDATE_PROFILE_PIC_GALLERY = 5000;
        public static final int UPDATE_PROFILE_PIC = 6000;
        public static final int CNIC_BACK = 10;
        public static final int CNIC_FRONT = 11;
        public static final int SALARY_SLIP = 12;
        public static final int UTILITY_BILL = 13;
        public static final int OTHER_DOC = 14;
    }
}