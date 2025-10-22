package entity;

import lombok.Data;

import java.util.List;

/**
 * @author liushixuan.6
 * @date 2025/10/22 10:02
 * @description:
 */
@Data
public class ActorFilms {
    private String actor;

    private List<String> movies;
}
