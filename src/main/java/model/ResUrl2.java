package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResUrl2 {
    @Builder.Default
    Boolean isSuccessful = false;
    @Builder.Default
    String headers = null;
    @Builder.Default
    int httpcode = -99;
    @Builder.Default
    String httpCodeMessage = null;
    @Builder.Default
    String stringBody = null;
    @Builder.Default
    String exceptionMessage = "";
    @Builder.Default
    long timeTakenInMilis = -99;
}

