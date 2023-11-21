package mmr.mosfik.SpringAuth.service.admin;

import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public String get() {
        return "GET:: admin service";
    }

    @Override
    public String post() {
        return "POST:: admin service";
    }

    @Override
    public String put() {
        return "PUT:: admin service";
    }

    @Override
    public String delete() {
        return "DELETE:: admin service";
    }
}

