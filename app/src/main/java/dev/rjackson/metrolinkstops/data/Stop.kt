package dev.rjackson.metrolinkstops.data

data class Stop(
    val name: String
) {
    fun toPreferenceString(): String = name

    companion object {
        fun String.toStop(): Stop = Stop(name = this)
    }
}
