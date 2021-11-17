package br.gov.basis.taskmanager.taskservice.domain.elastic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

@Getter
@Setter
@Document(indexName = "tm-comment", type = "comment")
@NoArgsConstructor
public class CommentDocument extends BaseDocument{

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String description;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String task;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String owner;

    public CommentDocument(Integer id, String description, String task, String owner) {
        this.id = id;
        this.description = description;
        this.task = task;
        this.owner = owner;
    }
}
