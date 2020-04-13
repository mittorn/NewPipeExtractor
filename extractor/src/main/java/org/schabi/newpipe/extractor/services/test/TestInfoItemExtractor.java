package org.schabi.newpipe.extractor.services.test;

import com.grack.nanojson.JsonObject;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
//import org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCParsingHelper;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;
import java.util.Calendar;
import javax.annotation.Nullable;

public class TestInfoItemExtractor implements StreamInfoItemExtractor {

    String itemName;

    public TestInfoItemExtractor(String itemname) {
        this.itemName = itemname;
    }

    @Override
    public StreamType getStreamType() throws ParsingException {
        return StreamType.VIDEO_STREAM;
    }

    @Override
    public boolean isAd() throws ParsingException {
        return false;
    }

    @Override
    public long getDuration() throws ParsingException {
        return 100;//event.getInt("length");
    }

    @Override
    public long getViewCount() throws ParsingException {
        return 1;//event.getInt("view_count");
    }

    @Override
    public String getUploaderName() throws ParsingException {
        return "test_uploader_name";//event.getString("conference_url")
                //.replace("https://api.media.ccc.de/public/conferences/", "");
    }

    @Override
    public String getUploaderUrl() throws ParsingException {
        return "http://example.com/test_uploader_url";//event.getString("conference_url");
    }

    @Nullable
    @Override
    public String getTextualUploadDate() throws ParsingException {
        return "1 april 2020";//event.getString("release_date");
    }

    @Nullable
    @Override
    public DateWrapper getUploadDate() throws ParsingException {
        return new DateWrapper(Calendar.getInstance()); //new DateWrapper(MediaCCCParsingHelper.parseDateFrom(getTextualUploadDate()));
    }

    @Override
    public String getName() throws ParsingException {
        return itemName;
    }

    @Override
    public String getUrl() throws ParsingException {
        return "http://example.com/"+itemName;
        // "https://api.media.ccc.de/public/events/" +
                //event.getString("guid");
    }

    @Override
    public String getThumbnailUrl() throws ParsingException {
        return "http://example.com/"+itemName+ ".jpg";
        //event.getString("thumb_url");
    }
}
