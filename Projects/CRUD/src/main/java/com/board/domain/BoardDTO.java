package com.board.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
public class BoardDTO extends CommonDTO {

    /** 번호 (PK) */
    private Long idx;

    /** 제목 */
    @NotBlank(message = "title가 null 또는 빈 값")
    private String title;

    /** 내용 */
    @NotBlank(message = "content가 null 또는 빈 값")
    private String content;

    /** 작성자 */
    @NotBlank(message = "writer가 null 또는 빈 값")
    private String writer;

    /** 조회 수 */
    private int viewCnt;

    /** 공지 여부 */
    private boolean noticeYn;

    /** 비밀 여부 */
    private boolean secretYn;

    /** 파일 변경 여부 */
    private boolean changeYn;

    /** 파일 인덱스 리스트 */
    private List<Long> fileIdxs;

}