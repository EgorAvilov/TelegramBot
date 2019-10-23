package by.minsk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CityDTO {
    private long id;
    private String name;
    private String description;

    public CityDTO() {
    }
}
