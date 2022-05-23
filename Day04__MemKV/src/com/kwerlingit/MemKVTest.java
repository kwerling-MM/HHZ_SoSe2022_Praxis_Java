package com.kwerlingit;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Files.deleteIfExists;
import static org.junit.jupiter.api.Assertions.*;

class MemKVTest {

    class tClass implements Serializable {}
    class tClass__NOT__Serializable {}

    String dataFName = "testdb.kv";

    void addData( MemKV mkv ) throws Exception {
        mkv.put("A", 42);
        mkv.put("b", Double.valueOf( 1.234) );
        mkv.put("C", Float.valueOf( 5.678f) );
        mkv.put("d", "TestMe" );
        mkv.put("E", "TestMe" );
        mkv.put("f", "TestMe" );
        mkv.put("G", "TestMe" );
        mkv.put("h", "TestMe" );
        mkv.put("I", "TestMe" );
        mkv.put("j", new BigInteger("9876543210".repeat( 23) ));
        mkv.put("K", Boolean.valueOf( true ) );
    }

    final String LK_k1o1i1 = "[Cust1][Order1][Item1]";
    final String LK_k1o1i2 = "[Cust1][Order1][Item2]";
    final String LK_k1o2i4 = "[Cust1][Order2][Item4]";
    final String LK_k1o2i7 = "[Cust1][Order2][Item7]";
    final String LK_k2o4i1 = "[Cust2][Order4][Item1]";
    final String LK_k2o5i1 = "[Cust2][Order5][Item1]";
    final String LK_k3o6i1 = "[Cust3][Order6][Item1]";
    final String LK_AAA_123 = "AAA_123";
    final String LK_BBB_123 = "BBB_123";
    final String LK_CCC_123 = "CCC_123";
    final String LK_AAA_456 = "AAA_456";
    final String LK_AAA_789 = "AAA_789";
    final String LK_BBB_098 = "BBB_098";
    final String LK_CCC_173 = "CCC_173";
    final String LK_CCC_121 = "CCC_121";
    final String LK_CCC_181 = "CCC_181";
    void addDataWithLongKeys( MemKV mkv ) throws Exception {
        mkv.put(LK_k1o1i1, Integer.valueOf( 42 ) );
        mkv.put(LK_k1o1i2, Double.valueOf( 1.234) );
        mkv.put(LK_k1o2i4, Integer.valueOf( 42 ) );
        mkv.put(LK_k1o2i7, Double.valueOf( 1.234) );
        mkv.put(LK_k2o4i1, Float.valueOf( 5.678f) );
        mkv.put(LK_k2o5i1, "TestMe" );
        mkv.put(LK_k3o6i1, "TestMe" );
        mkv.put(LK_AAA_123, "TestMe" );
        mkv.put(LK_BBB_123, "TestMe" );
        mkv.put(LK_CCC_123, "TestMe" );
        mkv.put(LK_AAA_456, "TestMe" );
        mkv.put(LK_AAA_789, new BigInteger("9876543210".repeat( 23) ));
        mkv.put(LK_BBB_098, Boolean.valueOf( true ) );
        mkv.put(LK_CCC_173, "TestMe" );
        mkv.put(LK_CCC_121, "TestMe" );
        mkv.put(LK_CCC_181, "TestMe" );
    }

    MemKV mkv = null;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        try {
            deleteIfExists( Paths.get( dataFName ) );
        } catch (IOException e) {
            /* not the best solution, but we do nothing */
        }

        mkv = new MemKV();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        mkv = null;
        try {
            deleteIfExists( Paths.get( dataFName ) );
        } catch (IOException e) {
            /* not the best solution, but we do nothing */
        }
    }



    @org.junit.jupiter.api.Test
    void vNotOfType() {
        String tStr = "MYTYPE";

        String exp = "Value not of type " + tStr + ".";
        String act = mkv.vNotOfType( tStr );
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void load() {

        persist();

        MemKV mkv1 = mkv;
        MemKV mkv2 = new MemKV();

        try {
            mkv2.load( dataFName );
        } catch (Exception e) {
            /* We do nothing as we assert the situation */
        }

        assertEquals( 11 , mkv1.size() );
        assertEquals( mkv1.size() , mkv2.size() );
        assertEquals( mkv1.get("A") , mkv2.get("A") );
        assertEquals( mkv1.get("b") , mkv2.get("b") );
        assertEquals( mkv1.get("C") , mkv2.get("C") );
        assertEquals( mkv1.get("d") , mkv2.get("d") );
        assertEquals( mkv1.get("E") , mkv2.get("E") );
        assertEquals( mkv1.get("f") , mkv2.get("f") );
        assertEquals( mkv1.get("G") , mkv2.get("G") );
        assertEquals( mkv1.get("h") , mkv2.get("h") );
        assertEquals( mkv1.get("I") , mkv2.get("I") );
        assertEquals( mkv1.get("j") , mkv2.get("j") );
        assertEquals( mkv1.get("K") , mkv2.get("K") );
    }

    @org.junit.jupiter.api.Test
    void persist() {

        assertDoesNotThrow( () -> { addData( mkv ); } );
        try {
            mkv.persist( dataFName );
        } catch (IOException e) {
           /* We do nothing as we assert the situation */
        }
        assertEquals( true, Files.exists( Paths.get(dataFName )) );
    }

    @org.junit.jupiter.api.Test
    void size() {
        int exp;
        int act;

        assertDoesNotThrow( () -> { addData( mkv ); } );
        exp = 11;
        act = mkv.size();
        assertEquals( exp , act );
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        boolean exp;
        boolean act;

        assertDoesNotThrow( () -> { addData( mkv ); } );
        mkv.delete("A");
        mkv.delete("b");
        mkv.delete("C");
        mkv.delete("d");
        mkv.delete("E");
        mkv.remove("f");
        mkv.remove("G");
        mkv.remove("h");
        mkv.remove("I");
        mkv.remove("j");
        mkv.remove("K");

        exp = true;
        act = mkv.isEmpty();
        assertEquals( exp , act );

    }

    @org.junit.jupiter.api.Test
    void containsKey() {
        boolean exp;
        boolean act;

        assertDoesNotThrow( () -> { addData( mkv ); } );
        exp = true;
        act = mkv.containsKey("A"); assertEquals( exp , act );
        act = mkv.containsKey("b"); assertEquals( exp , act );
        act = mkv.containsKey("C"); assertEquals( exp , act );
        act = mkv.containsKey("d"); assertEquals( exp , act );
        act = mkv.containsKey("E"); assertEquals( exp , act );
        act = mkv.containsKey("f"); assertEquals( exp , act );
        act = mkv.containsKey("G"); assertEquals( exp , act );
        act = mkv.containsKey("h"); assertEquals( exp , act );
        act = mkv.containsKey("I"); assertEquals( exp , act );
        act = mkv.containsKey("j"); assertEquals( exp , act );
        act = mkv.containsKey("K"); assertEquals( exp , act );

        exp = false;
        act = mkv.containsKey("L"); assertEquals( exp , act );
        act = mkv.containsKey("m"); assertEquals( exp , act );
        act = mkv.containsKey("N"); assertEquals( exp , act );
        act = mkv.containsKey("o"); assertEquals( exp , act );
        act = mkv.containsKey("P"); assertEquals( exp , act );
        act = mkv.containsKey("q"); assertEquals( exp , act );
        act = mkv.containsKey("R"); assertEquals( exp , act );
        act = mkv.containsKey("s"); assertEquals( exp , act );
        act = mkv.containsKey("T"); assertEquals( exp , act );
        act = mkv.containsKey("u"); assertEquals( exp , act );
        act = mkv.containsKey("V"); assertEquals( exp , act );
    }

    @org.junit.jupiter.api.Test
    void remove() {
        /* Tested with isEmpty */
    }

    @org.junit.jupiter.api.Test
    void delete() {
        /* Tested with isEmpty */
    }

    @org.junit.jupiter.api.Test
    void clear() {
        assertDoesNotThrow( () -> { mkv.put("A",  Integer.valueOf(23)); } );
        assertEquals( 1, mkv.size() );

        mkv.clear();
        assertEquals( 0, mkv.size() );
    }

    @org.junit.jupiter.api.Test
    void containsValue() {

        String valueNotInList = "Not in list";
        boolean exp_true = true;
        boolean exp_false = false;

        assertDoesNotThrow( () -> { addData( mkv ); } );

        assertEquals( exp_false, mkv.containsValue( valueNotInList ));
        assertEquals( exp_false, mkv.containsValue( Double.valueOf( 5.678) ));
        assertEquals( exp_false, mkv.containsValue( Float.valueOf( 1.234f) ));

        assertEquals( exp_true, mkv.containsValue(Integer.valueOf( 42 )));
        assertEquals( exp_true, mkv.containsValue(Double.valueOf( 1.234)));
        assertEquals( exp_true, mkv.containsValue(Float.valueOf( 5.678f)));
        assertEquals( exp_true, mkv.containsValue("Test" + "Me"));
        assertEquals( exp_true, mkv.containsValue(new BigInteger("9876543210".repeat( 23))));
        assertEquals( exp_true, mkv.containsValue(Boolean.valueOf( true )));
    }

    @org.junit.jupiter.api.Test
    void put() {

        /*  Verify that any object can be stored */

        assertDoesNotThrow( () -> { mkv.put("A", new tClass() ); } );
        Object o = mkv.get("A");
        assertTrue( o instanceof tClass );

        assertThrows(Exception.class,() -> mkv.put("A", new tClass__NOT__Serializable() ));

        /*  Verify that values for keys can be overwritten */
        int exp = 34;
        final Integer Int = Integer.valueOf(34);
        assertDoesNotThrow( () -> { mkv.put("B", Int ); } );
        int act = mkv.getInteger("B").intValue();
        assertEquals( exp, act );

        exp = 67;
        assertDoesNotThrow( () -> { mkv.put("B", Integer.valueOf(67)); } );
        act = mkv.getInteger("B").intValue();
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void putString() {
        String act = null;
        String exp = "Test tet forever!";

        mkv.putString("A", exp);
        act = mkv.getString( "A" );
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void putDouble() {
        Double act = null;
        Double exp = Double.valueOf(1/3);

        mkv.putDouble("A", exp);
        act = mkv.getDouble( "A" );
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void testPutDouble() {
        double act;
        double exp = 1/3;

        mkv.putDouble("A", exp);
        act = mkv.getDouble( "A" ).doubleValue();
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void putFloat() {
        Float act;
        Float exp = Float.valueOf( 1/7f );

        mkv.putFloat("A", exp);
        act = mkv.getFloat( "A" );
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void testPutFloat() {
        float act;
        float exp = 1/7f;

        mkv.putFloat("A", exp);
        act = mkv.getFloat( "A" ).floatValue();
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void putInteger() {
        Integer act;
        Integer exp = Integer.valueOf(98);

        mkv.putInteger("A", exp);
        act = mkv.getInteger( "A" );
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void testPutInteger() {
        int act;
        int exp = 78;

        mkv.putInteger("A", exp);
        act = mkv.getInteger( "A" ).intValue();
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void putBoolean() {
        Boolean act;
        Boolean exp = false;

        mkv.putBoolean("A", exp);
        act = mkv.getBoolean( "A" );
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void testPutBoolean() {
        boolean act;
        boolean exp = false;

        mkv.putBoolean("A", exp);
        act = mkv.getBoolean( "A" ).booleanValue();
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void putBigInteger() {
        BigInteger act;
        BigInteger exp = new BigInteger("12345678901234567890");

        mkv.putBigInteger("A", new BigInteger( "1234567890".repeat(2) ));
        act = mkv.getBigInteger( "A" );
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void get() {

        /* Verify that null is given back when the key does not exist */
        Object exp = null;
        Object act = new tClass(); // Just so it is not null from the get go

        act = mkv.get( "unknownKey");
        assertEquals( exp, act );
    }

    @org.junit.jupiter.api.Test
    void getString() {
        /* Already tested with the put test routine */
    }

    @org.junit.jupiter.api.Test
    void getDouble() {
        /* Already tested with the put test routine */
    }

    @org.junit.jupiter.api.Test
    void getFloat() {
        /* Already tested with the put test routine */
    }

    @org.junit.jupiter.api.Test
    void getInteger() {
        /* Already tested with the put test routine */
    }

    @org.junit.jupiter.api.Test
    void getBoolean() {
        /* Already tested with the put test routine */
    }

    @org.junit.jupiter.api.Test
    void getBigInteger() {
        /* Already tested with the put test routine */
    }

    @Test
    void getRegExFromSearchPatter() {
        HashMap<String, String> inp_exp = new HashMap<>();

        // Input, Expected
        inp_exp.put( "Hallo",    "^Hallo$" );
        inp_exp.put( "Eins * Vier",    "^Eins .* Vier$" );
        inp_exp.put( "Eins \\* Vier",    "^Eins \\* Vier$" );
        inp_exp.put( "[Eins][Zwei][Drei]",    "^\\[Eins\\]\\[Zwei\\]\\[Drei\\]$" );
        inp_exp.put( "Eins \\[Zwei]",    "^Eins \\\\[Zwei\\]$" );
        inp_exp.put( "{{]][[}}\\}\\}\\}",    "^\\{\\{\\]\\]\\[\\[\\}\\}\\\\}\\\\}\\\\}$" );
        inp_exp.put( "",    "^$" );
        inp_exp.put( "",    "^$" );
        inp_exp.put( "",    "^$" );
        inp_exp.put( "",    "^$" );
        inp_exp.put( "",    "^$" );
        inp_exp.put( "",    "^$" );

        for(Map.Entry<String, String> entry : inp_exp.entrySet()) {
            String input = entry.getKey();
            String exp   = entry.getValue();

            String act = mkv.getRegExFromSearchPatter( input );
            assertEquals( exp, act );
        }
    }

    @Test
    void findEntriesByKey() {

        MemKV hm = null;

        assertDoesNotThrow( () -> { addDataWithLongKeys( mkv ); } );

        // All orders with all the items for Customer1
        final String crit1 = "[Cust1]*";
        hm = mkv.findEntriesByKey( crit1 );
        assertEquals( 4, hm.size() );
        assertTrue( hm.containsKey( LK_k1o1i1 ));
        assertTrue( hm.containsKey( LK_k1o1i2 ));
        assertTrue( hm.containsKey( LK_k1o2i4 ));
        assertTrue( hm.containsKey( LK_k1o2i7 ));

        // All items for order2 for Customer1
        final String crit2 = "[Cust1][Order2]*";
        hm = mkv.findEntriesByKey( crit2 );
        assertEquals( 2, hm.size() );
        assertTrue( hm.containsKey( LK_k1o2i4 ));
        assertTrue( hm.containsKey( LK_k1o2i7 ));

        // All customers and orders who ordered item1
        final String crit3 = "*[Item1]";
        hm = mkv.findEntriesByKey( crit3 );
        assertEquals( 4, hm.size() );
        assertTrue( hm.containsKey( LK_k1o1i1 ));
        assertTrue( hm.containsKey( LK_k2o4i1 ));
        assertTrue( hm.containsKey( LK_k2o5i1 ));
        assertTrue( hm.containsKey( LK_k3o6i1 ));

        // All entries, which have a number in the 100 to 199 range
        final String crit4 = "*_1*";
        hm = mkv.findEntriesByKey( crit4 );
        assertEquals( 6, hm.size() );
        assertTrue( hm.containsKey( LK_AAA_123 ));
        assertTrue( hm.containsKey( LK_BBB_123 ));
        assertTrue( hm.containsKey( LK_CCC_123 ));
        assertTrue( hm.containsKey( LK_CCC_173 ));
        assertTrue( hm.containsKey( LK_CCC_121 ));
        assertTrue( hm.containsKey( LK_CCC_181 ));

        // All entries, which have a number in the 100 to 199 range and end in a 1
        final String crit5 = "*_1?1*";
        hm = mkv.findEntriesByKey( crit5 );
        assertEquals( 2, hm.size() );
        assertTrue( hm.containsKey( LK_CCC_121 ));
        assertTrue( hm.containsKey( LK_CCC_181 ));
    }
    @Test
    void testFindEntriesByKey_CaseInsensitiveSearch_True() {
        /* Not yet implemented */
    }



    @Test
    void forEach() {
        /* This is tested with the getCopyOfDB method */
    }

    @Test
    void getCopyOfDB() {
        assertDoesNotThrow( () -> { addData( mkv ); } );
        assertDoesNotThrow( () -> { addDataWithLongKeys( mkv ); } );

        assertEquals( 27, mkv.size() );

        MemKV tkv = mkv.getCopyOfDB();
        assertEquals( 27, mkv.size() );
        mkv.forEach(( k, v ) -> {
            assertEquals( tkv.get( k ), v );
        });
    }

    @Test
    void getListOfKeys() {
    }

    @Test
    void addOtherDB() {
        MemKV mkv_1 = new MemKV();
        MemKV mkv_2 = new MemKV();

        assertDoesNotThrow( () -> { addData( mkv_1 ); } );
        assertDoesNotThrow( () -> { addDataWithLongKeys( mkv_2 ); } );
        mkv.addOtherDB( mkv_1 );
        mkv.addOtherDB( mkv_2 );

        assertEquals( mkv_1.size() + mkv_2.size(),  mkv.size() );
        assertTrue( mkv_1.size()  >  0 );
        assertTrue( mkv_2.size()  >  0 );

        mkv_1.forEach(( k, v ) -> {
            assertEquals( mkv.get( k ), v );
        });

        mkv_2.forEach(( k, v ) -> {
            assertEquals( mkv.get( k ), v );
        });
    }

    @Test
    void testAddOtherDB_AddOnlyNewKeys_true() {
        final String oldKeyNewValueNoUpdate = "This is a new Value, which should not be updated!";
        final String newKeysValue           = "This is a new key, which should be added!";
        final String newKey                 = "NewNewNew";


        MemKV mkv_1 = new MemKV();

        assertDoesNotThrow( () -> { addDataWithLongKeys( mkv ); } );
        assertDoesNotThrow( () -> { addDataWithLongKeys( mkv_1 ); } );

        assertEquals( mkv_1.getString( LK_AAA_123 ), mkv.getString( LK_AAA_123 ));
        assertEquals( "TestMe", mkv.getString( LK_AAA_123 ) );

        mkv_1.putString( LK_AAA_123, oldKeyNewValueNoUpdate );
        mkv_1.putString( newKey, newKeysValue );

        assertEquals( mkv_1.getString( LK_AAA_123 )  , oldKeyNewValueNoUpdate );
        assertEquals( mkv_1.getString(newKey )  ,     newKeysValue );

        mkv.addOtherDB( mkv_1, true);

        assertEquals( mkv_1.size(), mkv.size() );
        assertEquals( newKeysValue, mkv.getString( newKey ));
        assertNotEquals( oldKeyNewValueNoUpdate, mkv.getString( LK_AAA_123 ));
    }


    /*
    final String LK_k1o1i1 = "[Cust1][Order1][Item1]";
    final String LK_k1o1i2 = "[Cust1][Order1][Item2]";
    final String LK_k1o2i4 = "[Cust1][Order2][Item4]";
    final String LK_k1o2i7 = "[Cust1][Order2][Item7]";
    final String LK_k2o4i1 = "[Cust2][Order4][Item1]";
    final String LK_k2o5i1 = "[Cust2][Order5][Item1]";
    final String LK_k3o6i1 = "[Cust3][Order6][Item1]";
    final String LK_AAA_123 = "AAA_123";
    final String LK_BBB_123 = "BBB_123";
    final String LK_CCC_123 = "CCC_123";
    final String LK_AAA_456 = "AAA_456";
    final String LK_AAA_789 = "AAA_789";
    final String LK_BBB_098 = "BBB_098";
    final String LK_CCC_173 = "CCC_173";
    final String LK_CCC_121 = "CCC_121";
    final String LK_CCC_181 = "CCC_181";

     */


}