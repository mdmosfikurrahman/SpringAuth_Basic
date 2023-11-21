package mmr.mosfik.SpringAuth.service;

import org.springframework.stereotype.Service;

@Service
public class ManagementServiceImpl implements ManagementService {

    @Override
    public String get() {
        return "GET:: management service";
    }

    @Override
    public String post() {
        return "POST:: management service";
    }

    @Override
    public String put() {
        return "PUT:: management service";
    }

    @Override
    public String delete() {
        return "DELETE:: management service";
    }
}

