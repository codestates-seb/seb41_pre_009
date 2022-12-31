package stackoverflow.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiResponseDto2<T> {
    private List<T> data;

    public MultiResponseDto2(List<T> data) {
        this.data = data;
    }
}