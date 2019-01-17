package com.example.wlf.meusconvidados.constants;

public class DatabaseContants
{
    private DatabaseContants()
    {

    }

    public static class GUEST
    {
        public static final String TABLE_NAME = "Guest";

        public static class COLUMNS
        {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String PRESENCE = "presence";
            public static final String DOCUMENT = "document";
        }
    }
}
