package br.gov.basis.taskmanager.taskservice.domain.elastic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Document(indexName = "tm-owner", type = "owner")
@NoArgsConstructor
public class OwnerDocument extends BaseDocument{

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String name;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String email;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = DATE_PATTERN)
            })
    private String birthDate;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String status;

    public OwnerDocument(Integer id, String name, String email, LocalDate birthDate, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate != null ? birthDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null;
        this.status = status;
    }
}
