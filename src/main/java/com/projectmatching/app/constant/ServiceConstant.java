package com.projectmatching.app.constant;

import java.util.regex.Pattern;

public class ServiceConstant {
    public static final int PAGING_SIZE = 8;
    public static final Pattern REGEX_EMAIL = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
    public static final int NAME_SIZE_MAX = 20;

}
