package in.zerogravity.marvelcomics.data.local.prefs;


import in.zerogravity.marvelcomics.data.PreferenceHelper;
import in.zerogravity.marvelcomics.data.remote.NetworkConfig;

public class SharedPreferenceHelper implements PreferenceHelper {
    @Override
    public String getApPublicKey() {

        return NetworkConfig.PUBLIC_KEY;
    }

    @Override
    public String getApiPrivateKey() {
        return NetworkConfig.PRIVATE_KEY;
    }
}
