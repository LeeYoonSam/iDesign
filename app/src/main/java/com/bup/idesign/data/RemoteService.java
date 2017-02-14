package com.bup.idesign.data;

import com.bup.idesign.BuildConfig;
import com.bup.idesign.data.remote.RemoteApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Albert-IM on 14/02/2017.
 */

public class RemoteService {

    public static final int CONNECT_TIMEOUT = 15;
    public static final int WRITE_TIMEOUT = 15;
    public static final int READ_TIMEOUT = 15;

    private static RemoteApi INSTANCE;

    public synchronized static RemoteApi getInstance() {

        if(INSTANCE == null ) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                    : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RemoteApi.ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RemoteApi.class);
        }

        return INSTANCE;
    }
}


//// 인증서 문제가 생길수 있을지 대비해서 소스코드 복사해놓기 - 출처: http://tiii.tistory.com/11 [안드로이드 개발 팁 블로그]
//
//    Retrofit과 OkHttp 는 Square社(https://square.github.io/)에서 만든 오픈라이브러리 입니다.
//        Retrofit : REST통신을 위한 클라이언트 라이브러리
//        OkHttp :HTTP& HTTP/2 통신 클라이언트 라이브러리
//        Rxandroid : 안드로이드에서 Observer 패턴, Iterator 패턴을 사용 할 수 있게 하는 라이브러리
//                                       이 글을 아래와 같은 디펜던시를 사용합니다.
//        dependencies
//                                       compile 'com.squareup.retrofit2:retrofit:2.0.0-beta3'
//                                       compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta3'
//                                       compile 'com.squareup.retrofit2:adapter-rxjava:2.0.0-beta3'
//                                       compile 'com.squareup.okhttp3:okhttp:3.0.1'
//                                       compile 'com.squareup.okhttp3:okhttp-urlconnection:3.0.1'
//                                       compile 'com.squareup.okhttp3:logging-interceptor:3.0.1'
//                                       compile 'com.google.code.gson:gson:2.5'
//
//
//                                       출처: http://tiii.tistory.com/11 [안드로이드 개발 팁 블로그]
//public class RestfulAdapter {
//    public static final int CONNECT_TIMEOUT = 15;
//    public static final int WRITE_TIMEOUT = 15;
//    public static final int READ_TIMEOUT = 15;
//    private static final String SERVER_URL = "https://api.server.net/";//2부터 url뒤에 /를 입력해야 합니다.
//
//    private static OkHttpClient client;
//    private static RemoteApi Interface;
//
//    public synchronized static RemoteApi getInstance() {
//        if (Interface == null) {
//            //통신로그를 확인하기 위한 부분
//            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            //쿠키 메니저의 cookie policy를 변경 합니다.
//            CookieManager cookieManager = new CookieManager(); cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
//
//            //OkHttpClient를 생성합니다.
//            client = configureClient(new OkHttpClient().newBuilder()) //인증서 무시
//            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
//            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
//            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
//            .cookieJar(new JavaNetCookieJar(cookieManager)) //쿠키메니져 설정
//            .addInterceptor(httpLoggingInterceptor) //http 로그 확인
//            .build();
//
//            //Retrofit 설정
//            Interface = new Retrofit.Builder()
//                    .baseUrl(DIRECTFOLDER_SERVER)
//                    .client(client)
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //Rxandroid를 사용하기 위해 추가(옵션)
//                    .addConverterFactory(GsonConverterFactory.create()) //Json Parser 추가
//                    .build().create(samaple_Interface.class); //인터페이스 연결
//            }
//        return Interface;
//    }
//
//    /** * UnCertificated 허용 */
//    public static OkHttpClient.Builder configureClient(final OkHttpClient.Builder builder) {
//        final TrustManager[] certs = new TrustManager[]{
//                new X509TrustManager() {
//                    @Override
//                    public X509Certificate[] getAcceptedIssuers() {
//                        return null;
//                    }
//
//                    @Override
//                    public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
//
//                    }
//
//                    @Override
//                    public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
//
//                    }
//                }
//        };
//
//        SSLContext ctx = null;
//
//        try {
//            ctx = SSLContext.getInstance("TLS");
//            ctx.init(null, certs, new SecureRandom());
//        } catch (final java.security.GeneralSecurityException ex) {
//            ex.printStackTrace();
//        }
//
//        try {
//            final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
//                @Override
//                public boolean verify(final String hostname, final SSLSession session) {
//                    return true;
//                }
//            };
//
//            builder.sslSocketFactory(ctx.getSocketFactory()).hostnameVerifier(hostnameVerifier);
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }
//
//        return builder;
//    }
//}


