package com.kirkley.guitar.tab;

import org.herac.tuxguitar.song.models.TGSong;

public class GuitarTab extends TGSong {
    // facade over TGSong

    private TabMetaData metaData;

    public TabMetaData getTabMetaData() {
        return new TabMetaData(this);
    }

}
