package at.sw2017xp3.regionalo;

import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Kevin on 26.04.2017.
 */

public class ProductDetailUnitTest {
    @Test
    public void isBioTest(){

     String bio = ProductDetailActivity.isBio(true);
    assertEquals("Ja",bio);
    }
}
