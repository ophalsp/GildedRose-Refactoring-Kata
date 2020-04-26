package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.GildedRose.AGED_BRIE;
import static com.gildedrose.GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT;
import static com.gildedrose.GildedRose.CONJURED_MANA_CAKE;
import static com.gildedrose.GildedRose.SULFURAS_HAND_OF_RAGNAROS;
import static org.approvaltests.combinations.CombinationApprovals.verifyAllCombinations;
import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    @Test
    void testLegacyCode() {
        verifyAllCombinations(this::update,
                new String[] {"foo", AGED_BRIE, SULFURAS_HAND_OF_RAGNAROS, BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT},
                new Integer[] {0, 5, 6, 10, 11}, //Use this sellIn values, defined with the help of code coverage
                new Integer[] {0, 1, 2, 49, 50}); //Use this quality values, defined with the help of code coverage
    }

    @Test
    void testConjured() {
        Item conjuredManaCake = update(CONJURED_MANA_CAKE, 4, 6);
        assertConjuredItem(conjuredManaCake, 3, 4);
        conjuredManaCake = update(conjuredManaCake);
        assertConjuredItem(conjuredManaCake, 2, 2);
        conjuredManaCake = update(conjuredManaCake);
        assertConjuredItem(conjuredManaCake, 1, 0);
        conjuredManaCake = update(conjuredManaCake);
        assertConjuredItem(conjuredManaCake, 0, 0);
        conjuredManaCake = update(conjuredManaCake);
        assertConjuredItem(conjuredManaCake, -1, 0);
    }

    private Item update(String name, int sellIn, int quality) {
        Item item = new Item(name, sellIn, quality);
        return update(item);
    }

    private Item update(Item item) {
        GildedRose app = new GildedRose(new Item[] {item});
        app.updateQuality();
        return item;
    }

    private void assertConjuredItem(Item item, int expectedSellIn, int expectedQuality) {
        assertThat(item.name).isEqualTo(CONJURED_MANA_CAKE);
        assertThat(item.sellIn).isEqualTo(expectedSellIn);
        assertThat(item.quality).isEqualTo(expectedQuality);
    }
}
