package br.gov.basis.taskmanager.taskservice.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaskFilter extends DefaultFilter implements BaseFilter, Serializable {
    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        addShouldTermQuery(queryBuilder, "startDate", query);
        addShouldTermQuery(queryBuilder, "endDate", query);

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder,"title", "owner", "status");

        return queryBuilder;
    }
}
