package my.masdico.viewmodel

object BasketballTeamData {
    private val basketballTeamName = arrayOf(
        "Chicago Bulls",
        "Los Angeles Lakers",
        "Boston Celtics"
    )
    private val basketballTeamDetail = arrayOf(
        "The Chicago Bulls was founded on January 16, 1966, and played its first game during the 1966–67 NBA season. Chicago Bulls are an American professional basketball team based in Chicago. The Bulls compete in the National Basketball Association (NBA) as a member of the league's Eastern Conference Central Division. The Bulls play their home games at the United Center, an arena on Chicago's West Side.",
        "The Los Angeles Lakers play their home games at Crypto.com Arena, an arena shared with the NBA's Los Angeles Clippers, the Los Angeles Sparks of the Women's National Basketball Association, and the Los Angeles Kings of the National Hockey League. The Los Angeles Lakers are an American professional basketball team based in Los Angeles. The Lakers compete in the National Basketball Association (NBA) as a member of the league's Western Conference Pacific Division. The Lakers are one of the most successful teams in the history of the NBA, tied with the Boston Celtics.",
        "The Boston Celtics was founded in 1946 as one of the league's original eight teams, the Celtics play their home games at TD Garden, which is also the home of the National Hockey League's Boston Bruins. The Boston Celtics are an American professional basketball team based in Boston. The Celtics compete in the National Basketball Association (NBA) as a member of the league's Eastern Conference Atlantic Division. The Celtics are one of the most successful teams in NBA history. The Celtics currently hold the record for the most recorded wins of any NBA team."
    )
    private val basketballTeamLogo = intArrayOf(
        R.drawable.nba_chicago_bulls,
        R.drawable.nba_la_lakers,
        R.drawable.nba_boston_celtics
    )
    val listTeam: ArrayList<BasketballTeam>
        get() {
            val list = arrayListOf<BasketballTeam>()
            for (position in basketballTeamName.indices){
                val basketballTeam = BasketballTeam()
                basketballTeam.name = basketballTeamName[position]
                basketballTeam.details = basketballTeamDetail[position]
                basketballTeam.imgLogo = basketballTeamLogo[position]
                list.add(basketballTeam)
            }
            return list
        }
}