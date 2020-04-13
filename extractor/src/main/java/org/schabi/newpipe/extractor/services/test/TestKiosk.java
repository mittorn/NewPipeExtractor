package org.schabi.newpipe.extractor.services.test;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.ChannelInfoItem;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemsCollector;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;

import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
//import org.schabi.newpipe.extractor.services.media_ccc.extractors.infoItems.MediaCCCConferenceInfoItemExtractor;
import org.schabi.newpipe.extractor.services.test.TestInfoItemExtractor;
import javax.annotation.Nonnull;
import java.io.IOException;

public class TestKiosk extends KioskExtractor<StreamInfoItem> {

    private JsonObject doc;

    public TestKiosk(StreamingService streamingService,
                                   ListLinkHandler linkHandler,
                                   String kioskId) {
        super(streamingService, linkHandler, kioskId);
    }

    @Nonnull
    @Override
    public InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        /*JsonArray conferences = doc.getArray("conferences");
        ChannelInfoItemsCollector collector = new ChannelInfoItemsCollector(getServiceId());
        for (int i = 0; i < conferences.size(); i++) {
            collector.commit(new MediaCCCConferenceInfoItemExtractor(conferences.getObject(i)));
        }

        return new InfoItemsPage<>(collector, "");*/
        StreamInfoItemsCollector collector = new StreamInfoItemsCollector(getServiceId());
        collector.commit(new TestInfoItemExtractor("item1"));
        return new InfoItemsPage<>(collector, "");
    }

    @Override
    public String getNextPageUrl() throws IOException, ExtractionException {
        return "";
    }

    @Override
    public InfoItemsPage<StreamInfoItem> getPage(String pageUrl) throws IOException, ExtractionException {
        return InfoItemsPage.emptyPage();
    }

    @Override
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        /*String site = downloader.get(getLinkHandler().getUrl(), getExtractorLocalization()).responseBody();
        try {
            doc = JsonParser.object().from(site);
        } catch (JsonParserException jpe) {
            throw new ExtractionException("Could not parse json.", jpe);
        }*/
    }

    @Nonnull
    @Override
    public String getName() throws ParsingException {
        return "TestKiosk";//doc.getString("Conferences");
    }
}
