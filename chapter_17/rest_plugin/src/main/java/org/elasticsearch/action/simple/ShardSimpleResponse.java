package org.elasticsearch.action.simple;

import org.elasticsearch.cluster.routing.ShardRouting;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Streamable;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class ShardSimpleResponse implements Streamable, ToXContent {

    private Set<String> simple;
    private ShardRouting shardRouting;

    ShardSimpleResponse() {
    }

    public ShardSimpleResponse(ShardRouting routing, Set<String> simple) {
        this.shardRouting = routing;
        this.simple = simple;
    }

    public Set<String> getTermList() {
        return simple;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        this.shardRouting = new ShardRouting(in);
        int n = in.readInt();
        simple = new HashSet<String>(n);
        for (int i = 0; i < n; i++) {
            simple.add(in.readString());
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        this.shardRouting.writeTo(out);
        out.writeInt(simple.size());
        for (String t : simple) {
            out.writeString(t);
        }
    }

    public static ShardSimpleResponse readShardResult(StreamInput in) throws IOException {
        ShardSimpleResponse result = new ShardSimpleResponse();
        result.readFrom(in);
        return result;
    }


    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(ShardSimpleResponse.Fields.ROUTING)
                .field(ShardSimpleResponse.Fields.STATE, shardRouting.state())
                .field(ShardSimpleResponse.Fields.PRIMARY, shardRouting.primary())
                .field(ShardSimpleResponse.Fields.NODE, shardRouting.currentNodeId())
                .field(ShardSimpleResponse.Fields.RELOCATING_NODE, shardRouting.relocatingNodeId())
                .endObject();

        builder.startArray(Fields.TERMS);
        for (String t : simple) {
            builder.value(t);
        }
        builder.endArray();
        return builder;
    }

    static final class Fields {
        static final String ROUTING = "routing";
        static final String STATE = "state";
        static final String PRIMARY = "primary";
        static final String NODE = "node";
        static final String RELOCATING_NODE = "relocating_node";
        static final String TERMS = "terms";
    }

}