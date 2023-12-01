package common

fun <T : Any> cycle(l: List<T>): Sequence<T> {
    var i = 0;
    if (l.isEmpty()) {
        return sequenceOf()
    }
    return generateSequence { l[i++ % l.size] }
}