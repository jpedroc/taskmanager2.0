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
public class OwnerFilter extends DefaultFilter implements BaseFilter, Serializable {
    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder,"name", "email", "status");

        addShouldTermQuery(queryBuilder, "birthDate", query);

        return queryBuilder;
    }
}
