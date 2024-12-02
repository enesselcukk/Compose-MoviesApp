object DependenciesWithVersions {

    // Core
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

    // Jetpack Compose
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3:${Versions.MATERIAL3}"
    const val COMPOSE_MATERIAL3_WINDOW = "androidx.compose.material3:material3-window-size-class:${Versions.MATERIAL3}"
    const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
    const val COMPOSE_UTIL = "androidx.compose.ui:ui-util:${Versions.COMPOSE}"
    const val COMPOSE_RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val COMPOSE_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"

    // Activity & ViewModel
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_COMPOSE}"
    const val VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME_COMPOSE = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.LIFECYCLE}"

    // Navigation
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Versions.NAVIGATION}"

    // Accompanist
    const val ACCOMPANIST_INSETS =
        "com.google.accompanist:accompanist-insets:${Versions.ACCOMPANIST_INSETS}"
    const val ACCOMPANIST_NAV_ANIMATION =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.ACCOMPANIST_NAV_ANIMATION}"
    const val ACCOMPANIST_PAGER =
        "com.google.accompanist:accompanist-pager:${Versions.ACCOMPANIST_PAGER}"

    // ConstraintLayout
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.CONSTRAINT_LAYOUT}"

    // Coroutines
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_ANDROID}"
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE}"

    // Room
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_PAGING = "androidx.room:room-paging:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"

    // Hilt
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    const val HILT_NAVIGATION_COMPOSE =
        "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE}"

    // Retrofit & Moshi
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val RETROFIT_MOCK = "com.squareup.retrofit2:retrofit-mock:${Versions.RETROFIT}"
    const val RETROFIT_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"

    // OkHttp
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    const val OKHTTP_MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP}"

    // Coil
    const val COIL = "io.coil-kt:coil-compose:${Versions.COIL}"

    // Paging
    const val PAGING_COMPOSE = "androidx.paging:paging-compose:${Versions.PAGING}"
    const val PAGING_RUNTIME = "androidx.paging:paging-runtime-ktx:${Versions.PAGING}"

    // DataStore
    const val DATASTORE = "androidx.datastore:datastore:${Versions.DATASTORE}"
    const val DATASTORE_CORE = "androidx.datastore:datastore-core:${Versions.DATASTORE}"
    const val DATASTORE_PREFERENCES =
        "androidx.datastore:datastore-preferences:${Versions.DATASTORE}"

    // Protobuf
    const val PROTOBUF = "com.google.protobuf:protobuf-javalite:${Versions.PROTOBUF}"

    // Lottie
    const val LOTTIE = "com.airbnb.android:lottie-compose:${Versions.LOTTIE}"

    // ExoPlayer
    const val MEDIA3_EXOPLAYER = "androidx.media3:media3-exoplayer:${Versions.MEDIA3}"
    const val MEDIA3_UI = "androidx.media3:media3-ui:${Versions.MEDIA3}"

    // Network Debugging
    const val CHUCKER = "com.github.chuckerteam.chucker:library:${Versions.CHUCKER}"

    // WorkManager
    const val WORK_RUNTIME = "androidx.work:work-runtime-ktx:${Versions.WORK}"

    // Test Dependencies
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val HAMCREST = "org.hamcrest:hamcrest-all:${Versions.HAMCREST}"
    const val ARCH_CORE_TESTING = "android.arch.core:core-testing:${Versions.ARCH_CORE_TESTING}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:${Versions.MOCKITO}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Versions.MOCKITO}"
    const val MOCKITO_ANDROID = "org.mockito:mockito-android:${Versions.MOCKITO_ANDROID}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val TURBINE = "app.cash.turbine:turbine:${Versions.TURBINE}"
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val ANDROID_TEST_CORE = "androidx.test:core:${Versions.ANDROID_TEST_CORE}"
    const val COMPOSE_TEST_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
    const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_TEST}"


}