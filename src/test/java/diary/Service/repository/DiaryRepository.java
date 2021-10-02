package diary.Service.repository;

import web.diaryservice.domain.Diary;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository {
    List<Diary> diaryList(HttpServletRequest request);//다이어리 리스트를 가져옴
    Optional<Diary> findById(Long id);//id로 해당 다이어리를 찾아옴 (delete에 쓰기위함), id를 자동으로 1부터 늘리는방식으로 지정해야겠음
    void save(Diary diary, HttpServletRequest request);//다이어리 내용저장
    void update(Diary diary);//request가 필요없음 같은 유저가 수정할거기때문에
    void delete(Diary diary);//해당 다이어리 삭제
}
