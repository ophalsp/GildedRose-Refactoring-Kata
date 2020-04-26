package com.gildedrose;

class GildedRose {

    private static final int MAXIMUM_QUALITY = 50;
    private static final int MINIMUM_QUALITY = 0;

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            update(item);
        }
    }

    private void update(Item item) {
        if (AGED_BRIE.equals(item.name)) {
            updateAgedBrie(item);
        } else if (BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT.equals(item.name)) {
            updateBackstagePasses(item);
        } else if (SULFURAS_HAND_OF_RAGNAROS.equals(item.name)) {
            updateSulfuras(item);
        } else if (CONJURED_MANA_CAKE.equals(item.name)) {
            updateConjured(item);
        } else {
            updateDefault(item);
        }
    }

    private void updateAgedBrie(Item item) {
        incrementQualityWhenMaximumQualityIsNotReached(item);

        decrementSellIn(item);

        if (item.sellIn < 0) {
            incrementQualityWhenMaximumQualityIsNotReached(item);
        }
    }

    private void updateBackstagePasses(Item item) {
        incrementQualityWhenMaximumQualityIsNotReached(item);
        if (item.sellIn < 11) {
            incrementQualityWhenMaximumQualityIsNotReached(item);
        }
        if (item.sellIn < 6) {
            incrementQualityWhenMaximumQualityIsNotReached(item);
        }
        decrementSellIn(item);

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void updateSulfuras(Item item) {
        item.quality = 80;
    }

    private void updateConjured(Item item) {
        for (int i = 0; i < 2; i++) {
            decrementQualityWhenMinimumQualityIsNotReached(item);
        }
        decrementSellIn(item);
    }

    private void updateDefault(Item item) {
        decrementQualityWhenMinimumQualityIsNotReached(item);

        decrementSellIn(item);

        if (item.sellIn < 0) {
            decrementQualityWhenMinimumQualityIsNotReached(item);
        }
    }

    private void decrementSellIn(Item item) {
        item.sellIn--;
    }

    private void decrementQualityWhenMinimumQualityIsNotReached(Item item) {
        if (item.quality > MINIMUM_QUALITY) {
            item.quality--;
        }
    }

    private void incrementQualityWhenMaximumQualityIsNotReached(Item item) {
        if (item.quality < MAXIMUM_QUALITY) {
            item.quality++;
        }
    }

}