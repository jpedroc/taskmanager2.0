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
@Document(indexName = "tm-task", type = "task")
@NoArgsConstructor
public class TaskDocument extends BaseDocument {

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String title;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String type;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = DATE_PATTERN)
            })
    private String expectedStartDate;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = DATE_PATTERN)
            })
    private String expectedEndDate;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = DATE_PATTERN)
            })
    private String startDate;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = DATE_PATTERN)
            })
    private String endDate;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String status;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)
            })
    private String owner;

    public TaskDocument(Integer id, String title, LocalDate expectedStartDate, LocalDate expectedEndDate, LocalDate startDate, LocalDate endDate, String status, String owner) {
        this.id = id;
        this.title = title;
        this.expectedStartDate = expectedStartDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
        this.expectedEndDate = expectedEndDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
        this.startDate = startDate != null ? startDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null;
        this.endDate = endDate != null ? endDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null;
        this.status = status;
        this.owner = owner;
    }

}
