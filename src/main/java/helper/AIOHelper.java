package helper;

public class AIOHelper {
    private enum aioAuth {
        yudi("asd");

        private String auth;
        aioAuth(String auth) {
          this.auth = auth;
        }
    }

    private String getAuthAIO(String user) {
        try {
            aioAuth aio = aioAuth.valueOf(user);
            return aio.auth;
        } catch (Exception e) {
            return null;
        }
    }
}
