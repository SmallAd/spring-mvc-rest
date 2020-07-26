package my.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Customer")
public class CustomerDTO {

    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstname;
    private String lastname;

    @JsonProperty("customer_url")
    private String customerUrl;
}
