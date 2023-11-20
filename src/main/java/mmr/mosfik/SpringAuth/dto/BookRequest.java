package mmr.mosfik.SpringAuth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRequest {

    private String authorName;
    private String isbnNumber;
}
