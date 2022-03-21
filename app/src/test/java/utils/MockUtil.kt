package utils

import com.example.toycomposenavermovie.data.remote.response.Movie
import com.example.toycomposenavermovie.data.remote.response.MovieResponse
import com.example.toycomposenavermovie.domain.model.NaverMovie


object MockUtil {

    fun mockNaverReponse() = MovieResponse(
        display = 1,
        movieList = listOf(mockMovie()),
        lastBuildDate = "",
        start = 0,
        total = 10
    )

    fun mockMovie() = Movie(
        actor = "김혜수|유아인|허준호|조우진|뱅상 카셀|",
        director = "최국희|",
        image = "https://ssl.pstatic.net/imgmovie/mdi/mit110/1641/164192_P45_134107.jpg",
        link = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=164192",
        pubDate = "2018",
        subtitle = "Default",
        title = "<b>국가</b>부도의 날",
        userRating = "8.12"
    )


    fun mockNaverMovie() = NaverMovie(
        actor = "김혜수|유아인|허준호|조우진|뱅상 카셀|",
        director = "최국희|",
        image = "https://ssl.pstatic.net/imgmovie/mdi/mit110/1641/164192_P45_134107.jpg",
        link = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=164192",
        pubDate = "2018",
        subtitle = "Default",
        title = "<b>국가</b>부도의 날",
        userRating = "8.12"
    )
}