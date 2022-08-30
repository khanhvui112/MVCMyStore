package com.sanvui.utils;

import com.sanvui.model.dto.param.MailParamDto;
import com.sanvui.model.entity.Employee;

/**
 * @author: VuiSK
 * @created: 04/12/2021-6:19 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class MailParamDtoUtil {

    public static MailParamDto mailParamDto(Employee employee, int time, String url) {
        return MailParamDto.builder()
                .mailTo(new String[]{employee.getEmail()})
                .subject("[My Store]Kích hoạt tài khoản " + employee.getUserName())
                .content("<h3>Xin chào Quý khách, </h3>" +
                        "<p>Chào mừng bạn đến với My Store. Cảm ơn Qúy khách đã đăng ký tài khoản trên website.</p>" +
                        "<p> Vui lòng vào đường dẫn dưới đây để xác nhận email " + employee.getEmail() + " và kích hoạt tài khoản của Quý khách:</p>" +
                        "<a href=\"" + url + "/active-account?active-token=" + employee.getTokenActive() + "\"" + ">" + url + "/active-account?active-token=" + employee.getTokenActive() + "</a> <br>" +
                        "<p>Sau khi xác nhận địa chỉ email thành công. Quý khách có thể đăng nhập vào website My Store và bắt" +
                        " đầu đăng ký sử dụng các dịch vụ của chúng tôi</p>" +
                        "<p>(Lưu ý email này chỉ có tác dụng " + time + " phút nếu quá hạn quý khách có thể yêu cầu xác nhận email mới)</p>")
                .build();
    }
}
