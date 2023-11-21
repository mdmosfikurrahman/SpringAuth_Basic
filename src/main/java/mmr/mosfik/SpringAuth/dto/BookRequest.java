package mmr.mosfik.SpringAuth.dto;

import lombok.Data;

@Data
public class BookRequest {
    private Integer bookId;
    private String authorName;
    private String isbnNumber;
}

