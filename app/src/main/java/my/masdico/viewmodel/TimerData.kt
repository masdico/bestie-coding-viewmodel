package my.masdico.viewmodel

object TimerData {
    private val time = arrayOf(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5)
    val counter = time
    val dropDownData: ArrayList<String>
        get() {
            val list = arrayListOf<String>()
            for (element in time.indices) {
                list.add("${time[element]} second")
            }
            return list
        }
}