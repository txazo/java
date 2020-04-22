package org.txazo.wyot.es;

import com.alibaba.fastjson.JSON;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.Update;
import io.searchbox.core.search.sort.Sort;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class JestClientHelper {

    @Autowired
    private JestClient jestClient;

    public boolean index(String index, String type, String id, Object source, boolean refresh) throws IOException {
        Index.Builder builder = new Index.Builder(source);
        builder.id(id);
        Index indexAction = builder.index(index).type(type).refresh(refresh).build();
        JestResult result = jestClient.execute(indexAction);
        return result.isSucceeded();
    }

    public boolean update(String index, String type, String id, Object source, boolean refresh) throws IOException {
        Update update = new Update.Builder(getUpdateScript(JSON.toJSONString(source))).index(index).type(type).id(id).refresh(refresh).build();
        JestResult result = jestClient.execute(update);
        return result.isSucceeded();
    }

    public JestResult search(String index, String type, QueryBuilder query, int from, int size, List<Sort> sorts) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).from(from).size(size);
        Search.Builder builder = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(index)
                .addType(type);
        if (CollectionUtils.isNotEmpty(sorts)) {
            builder.addSort(sorts);
        }

        return jestClient.execute(builder.build());
    }

    public <T> T searchObject(String index, String type, QueryBuilder query, Class<T> classType) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query);
        Search.Builder builder = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(index)
                .addType(type);

        JestResult result = jestClient.execute(builder.build());
        return result.getSourceAsObject(classType);
    }

    private String getUpdateScript(String jsonStr) {
        return "{\"doc\":" + jsonStr + "}";
    }

}
