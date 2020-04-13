package org.schabi.newpipe.extractor.services.test;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.stream.*;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;

public class TestStreamExtractor extends StreamExtractor {

//    private JsonObject data;
//    private JsonObject conferenceData;

    public TestStreamExtractor(StreamingService service, LinkHandler linkHandler) {
        super(service, linkHandler);
    }

    @Nonnull
    @Override
    public String getTextualUploadDate() throws ParsingException {
        return "1 april 2020";//data.getString("release_date");
    }

    @Nonnull
    @Override
    public DateWrapper getUploadDate() throws ParsingException {
        return new DateWrapper(Calendar.getInstance());
    }

    @Nonnull
    @Override
    public String getThumbnailUrl() throws ParsingException {
        return "http://example.com/thumb.jpg";
    }

    @Nonnull
    @Override
    public Description getDescription() throws ParsingException {
        return new Description("Description", Description.PLAIN_TEXT);
    }

    @Override
    public int getAgeLimit() throws ParsingException {
        return 0;
    }

    @Override
    public long getLength() throws ParsingException {
        return 100;
    }

    @Override
    public long getTimeStamp() throws ParsingException {
        return 0;
    }

    @Override
    public long getViewCount() throws ParsingException {
        return 1;//data.getInt("view_count");
    }

    @Override
    public long getLikeCount() throws ParsingException {
        return -1;
    }

    @Override
    public long getDislikeCount() throws ParsingException {
        return -1;
    }

    @Nonnull
    @Override
    public String getUploaderUrl() throws ParsingException {
        return "http://example.com/uploader_url";//data.getString("conference_url");
    }

    @Nonnull
    @Override
    public String getUploaderName() throws ParsingException {
        return "uploader_name";//data.getString("conference_url")
                //.replace("https://api.media.ccc.de/public/conferences/", "");
    }

    @Nonnull
    @Override
    public String getUploaderAvatarUrl() throws ParsingException {
        return "http://example.com/uploader_url.jpg";//;conferenceData.getString("logo_url");
    }

    @Nonnull
    @Override
    public String getDashMpdUrl() throws ParsingException {
        return "";
    }

    @Nonnull
    @Override
    public String getHlsUrl() throws ParsingException {
        return "";
    }

    @Override
    public List<AudioStream> getAudioStreams() throws IOException, ExtractionException {
        /*final JsonArray recordings = data.getArray("recordings");
        final List<AudioStream> audioStreams = new ArrayList<>();
        for (int i = 0; i < recordings.size(); i++) {
            final JsonObject recording = recordings.getObject(i);
            final String mimeType = recording.getString("mime_type");
            if (mimeType.startsWith("audio")) {
                //first we need to resolve the actual video data from CDN
                final MediaFormat mediaFormat;
                if (mimeType.endsWith("opus")) {
                    mediaFormat = MediaFormat.OPUS;
                } else if (mimeType.endsWith("mpeg")) {
                    mediaFormat = MediaFormat.MP3;
                } else if (mimeType.endsWith("ogg")) {
                    mediaFormat = MediaFormat.OGG;
                } else {
                    throw new ExtractionException("Unknown media format: " + mimeType);
                }

                audioStreams.add(new AudioStream(recording.getString("recording_url"), mediaFormat, -1));
            }
        }
        return audioStreams;*/
        final List<AudioStream> audioStreams = new ArrayList<>();
        audioStreams.add(new AudioStream("https://file-examples.com/wp-content/uploads/2017/11/file_example_OOG_5MG.ogg", MediaFormat.OGG, -1));
        
        return audioStreams;
    }

    @Override
    public List<VideoStream> getVideoStreams() throws IOException, ExtractionException {
        /*final JsonArray recordings = data.getArray("recordings");
        final List<VideoStream> videoStreams = new ArrayList<>();
        for (int i = 0; i < recordings.size(); i++) {
            final JsonObject recording = recordings.getObject(i);
            final String mimeType = recording.getString("mime_type");
            if (mimeType.startsWith("video")) {
                //first we need to resolve the actual video data from CDN

                final MediaFormat mediaFormat;
                if (mimeType.endsWith("webm")) {
                    mediaFormat = MediaFormat.WEBM;
                } else if (mimeType.endsWith("mp4")) {
                    mediaFormat = MediaFormat.MPEG_4;
                } else {
                    throw new ExtractionException("Unknown media format: " + mimeType);
                }

                videoStreams.add(new VideoStream(recording.getString("recording_url"),
                        mediaFormat, recording.getInt("height") + "p"));
            }
        }
        return videoStreams;
        */
        final List<VideoStream> videoStreams = new ArrayList<>();
        videoStreams.add(new VideoStream("http://techslides.com/demos/sample-videos/small.mp4",
                        MediaFormat.MPEG_4, "default mp4"));
        videoStreams.add(new VideoStream("http://techslides.com/demos/sample-videos/small.webm",
                        MediaFormat.WEBM, "default webm"));
        return videoStreams;
    }

    @Override
    public List<VideoStream> getVideoOnlyStreams() throws IOException, ExtractionException {
        return null;
    }

    @Nonnull
    @Override
    public List<SubtitlesStream> getSubtitlesDefault() throws IOException, ExtractionException {
        return Collections.emptyList();
    }

    @Nonnull
    @Override
    public List<SubtitlesStream> getSubtitles(final MediaFormat format) throws IOException, ExtractionException {
        return Collections.emptyList();
    }

    @Override
    public StreamType getStreamType() throws ParsingException {
        return StreamType.VIDEO_STREAM;
    }

    @Override
    public StreamInfoItem getNextStream() throws IOException, ExtractionException {
        return null;
    }

    @Override
    public StreamInfoItemsCollector getRelatedStreams() throws IOException, ExtractionException {
        return new StreamInfoItemsCollector(getServiceId());
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        /*try {
            data = JsonParser.object().from(
                    downloader.get(getLinkHandler().getUrl()).responseBody());
            conferenceData = JsonParser.object()
                    .from(downloader.get(getUploaderUrl()).responseBody());
        } catch (JsonParserException jpe) {
            throw new ExtractionException("Could not parse json returned by url: " + getLinkHandler().getUrl(), jpe);
        }*/
    }

    @Nonnull
    @Override
    public String getName() throws ParsingException {
        return "name";//data.getString("title");
    }

    @Nonnull
    @Override
    public String getOriginalUrl() throws ParsingException {
        return "http://example.com/original_url";//data.getString("frontend_link");
    }

    @Override
    public String getHost() throws ParsingException {
        return "";
    }

    @Override
    public String getPrivacy() throws ParsingException {
        return "";
    }

    @Override
    public String getCategory() throws ParsingException {
        return "";
    }

    @Override
    public String getLicence() throws ParsingException {
        return "";
    }

    @Override
    public Locale getLanguageInfo() throws ParsingException {
        return null;
    }

    @Nonnull
    @Override
    public List<String> getTags() throws ParsingException {
        return new ArrayList<>();
    }

    @Nonnull
    @Override
    public String getSupportInfo() throws ParsingException {
        return "";
    }
}
