package at.sw2017xp3.regionalo;

import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Kevin on 26.04.2017.
 */

public class ProductDetailUnitTest {
    @Test
    public void isBioTestForTrue() {
        String bio = ProductDetailActivity.isBio(true);
        assertEquals("Ja", bio);
    }

    @Test
    public void isBioTestForFalse() {
        String bio = ProductDetailActivity.isBio(false);
        assertEquals("Nein", bio);
    }

    @Test
    public void testCategorieFleisch() {
        assertEquals("Fleisch", ProductDetailActivity.productCategorieName(1));
    }

    @Test
    public void testCategorieObst() {
        assertEquals("Obst", ProductDetailActivity.productCategorieName(2));
    }

    @Test
    public void testCategorieGemüuese() {
        assertEquals(ProductDetailActivity.productCategorieName(3), "Gemüse");
    }

    @Test
    public void testCategorieMilchprodukte() {
        assertEquals("Milchprodukte", ProductDetailActivity.productCategorieName(4));
    }

    @Test
    public void testCategorieGetreide() {
        assertEquals("Getreide", ProductDetailActivity.productCategorieName(5));
    }

    @Test
    public void testCategorieSonstiges() {
        assertEquals("Sonstiges", ProductDetailActivity.productCategorieName(6));
    }

    @Test
    public void testLike() {

    }
}
