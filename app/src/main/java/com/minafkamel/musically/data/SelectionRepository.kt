package com.minafkamel.musically.data

import io.reactivex.Completable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class SelectionRepository {

    /**
     * A [PublishSubject] for anyone interested in listening to the selection of a song
     */
    val selectionPublisher: Subject<Pair<String, String>> = PublishSubject.create()

    /**
     * Sets the title and stream url for a selected song and provides the observer with a new item
     */
    fun setSongSelection(title: String, streamUrl: String): Completable {
        return Completable.fromAction { selectionPublisher.onNext(Pair(title, streamUrl)) }
    }
}