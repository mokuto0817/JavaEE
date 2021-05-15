package com.idle.enums;

public enum ReturnCodeEnum {

    //其他
    QUERY_FAILS("001", "查询失败"),
    LIVE_QUERY_FAILS("001", "暂时没有到开播时间或已过开播时间"),
    SAVE_FAILS("002", "保存失败"),
    REMOVE_FAILS("003", "删除失败"),
    UPDATE_FAILS("004", "修改失败"),
    SAVE_SUCCESS("005", "保存成功"),
    REMOVE_SUCCESS("006", "删除成功"),
    UPDATE_SUCCESS("007", "修改成功"),
    CODE_TIME_OUT("008", "验证码超时或无效,请重新获取验证码"),
    PHONE_CHCEK_NULL("009", "此手机号未绑定用户"),
    PHONE_CHCEK_NULL_TWO("010", "该手机未绑定登录账号!请联系管理员"),
    UPDATE_PHONE_FAILS("011","该手机号已被绑定"),
    UPDATE_QQ_FAILS("012","该QQ号已被绑定"),
    BANG_DING_WEI_XIN_FAILS("013","该微信号已被绑定"),
    OPERATION_SUCCESS("011", "修改成功"),
    PARAM_ILLEGAL("012", "参数非法"),
    //    消息
    INFO_MSG("001", "您收到一条新的消息"),
    MESSAGE_SAVE_SUCCESS("005", "消息推送成功"),
    MESSAGE_SAVE_FAILS("002", "消息推送失败"),
    //登录
    WRONG_PASSWORD("001", "账号或密码错误，再输错n次该用户将被锁定15分钟。"),
    LOGIN_ERROR("002", "登录出错，请重试！"),
    LOGIN_FAILED("003", "登录失败"),
    LOGIN_SUCCESS("004", "登录成功"),
    UPDATE_PHONE_ERROR("005", "更新手机号失败"),
    CODE_CHECK_ERROR("006", "验证码错误"),
    SPECIAL_CHARACTERS_ERROR("007", "昵称包含特殊字符"),
    PHONE_EXIST("008", "手机号已存在"),
    PHONE_NOT_EXIST("008", "手机号不存在"),
    PHONE_NOT_EXIST_SYSTEM("008", "手机号在系统中不存在"),
    FIND_APPID_ERROR("009", "未找到对应appId=[%s]的配置，请核实！"),
    REQUEST_ILLEGALITY("010", "非法请求"),
    WRONG_VERIFICATION_CODE("011", "账号或验证码错误"),
    LOGIN_NUMBER("012", "用户锁定，请15分钟后再试。"),
    VERUFICATION_FAULED("013", "验证失败"),
    INCONSISTENT_PASSWORDS("014", "两次密码不一致"),
    WRONG_CODE("015", "验证码发送次数过多，请15分钟后再试"),
    NEW_PASSWORD_NOT_OLD("015", "新密码与旧密码不能一致"),
    //读者
    READER_IS_NO_CARD("001", "无借阅证"),
    READER_INCONSISTENT("002", "实名认证身份证信息和借阅证身份信息不一致，请核对两者身份证信息"),
    READER_BINDING("003", "借阅证号或密码错误"),
    READER_UNBINDING("004", "借阅证解绑失败"),
    READER_OTHER_PERSON_BINDING("005", "借阅证已被其他人绑定，需要解绑后才能绑定"),
    READER_BINDING_NUMBER("006", "借阅证最多绑定5张，请先解绑后再绑定"),
    READER_QRCODE_OUTTIME("007", "验证码已超时"),

    //积分商城
    EXCHANGE_COUDE("001", "库存不足"),
    EXCHANGE_ERROR("002", "兑换失败"),
    WRITE_OFF_ERROR("003", "核销失败"),
    INTEGRAL_INSUFFICIENT_ERROR("004", "兑换失败，积分不足"),

    //支付
    PAY_ERROR("001", "支付请求失败"),
    PAY_SUCCESS("002", "支付请求成功"),
    REFUND_ERROR("003", "退款失败"),
    //活动
    ACTIVITY_SIGNIN_ERROR("005", "抱歉，可能因为部分内容不需要补签，所以补签失败了哦，请刷新下页面查看哟~"),
    ACTIVITY_SIGNIN_SUCCESS("006", "补签成功"),
    ACTIVITY_EVENT_ERROR("007", "抱歉，报名人数已达到上限，审核不能通过！"),
    ACTIVITY_EVENT_SUCCESS("008", "报名审核成功！"),
    ACTIVITY_EVENTR_ERROR("009", "报名审核失败！"),

    //馆员档案
    GET_QR_CODE_ERROR("001", "二维码获取失败"),
    LIB_IS_NO_ROOT("002", "暂无权限"),

    IDCARD_VERIFY_ERROR("005", "身份证与姓名不匹配"),
    IDCARD_EXIST("006", "身份证已绑定其他账号，不可重复绑定"),

    IMAGE_REGEX_ERROR("003", "图片格式有误"),

    //JWT token
    TOKEN_FAILED("001", "token 校验失败"),
    TOKEN_OVERDUE("002", "token 已经过期"),
    TOKEN_UNMATCHED("003", "token 不匹配"),
    TOKEN_BUILD_ERROR("004", "token 构建错误"),
    TOKEN_VERIFICATION_FAILED("005", "token 验证签名失败"),
    TOKEN_ILLEGALITY("006", "token 非法"),
    TOKEN_AUTH_FAILED("007", "授权失败"),
    TOKEN_CODE_ERROR("008", "授权码错误"),

    // 收藏
    COLLECTION_SUCCESS("001", "收藏成功"),
    COLLECTION_ERROR("002", "收藏失败"),
    // 提交
    SUBMIT_SUCCESS("001", "提交成功"),
    SUBMIT_ERROR("002", "提交失败"),
    // 权限
    CODE_EXIST("001", "编码已存在"),
    CODE_NAME_EXIST("002", "编码或名称已存在"),

    // 邮箱检查
    EMAIL_CHECK_SUCCESS("001", "成功"),
    EMAIL_CHECK_ERROR("002", "失败，请确认邮箱与授权密码是否正确"),

    CANCEL_SUCCESS("001", "取消成功"),
    CANCEL_ERROR("002", "取消失败"),

    GET_SYSUSER_ERROR("001", "获取后台个人信息失败"),
    GET_BASEUSER_ERROR("001", "获取移动端个人信息失败"),
    ACTIVITY_APPLY_EXIST("002", "用户已报名!"),
    ACTIVITY_APPLY_TOO("003", "报名人数已达上限!"),
    ACTIVITY_WORKS_EXISTS("003", "系统中已存在该作品!"),

    //预约
    REGISTER_RESERVE_SUCCESS("001", "预约成功"),
    REGISTER_RESERVE_ERROR("002", "预约失败! 请查询是否已经预约过"),
    CANCEL_RESERVE_ERROR("003", "取消失败"),
    RESERVE_ERROR("004", "已经预约过了"),


    SON_BIND_ERROR("002", "请勿重复绑定相同用户!"),
    DISPLAYCOLUMN_TO_MANY_ERROR("001", "该栏目已绑定超过5个"),
    DISPLAYCOLUMN_TO_ONE_ERROR("001", "该栏目只能绑定1个"),
    IDENTIFY("001", "我不太理解你的意思，再给我一点时间学习吧"),
    USER_EMAIL_EXIST("001", "邮箱已被绑定，请更换邮箱!"),

    // 续借
    RE_NEW_BOOK_SUCCESS("001", "续借成功"),
    RE_NEW_BOOK_ERROR("002", "续借失败"),

    // app
    APP_VERSION_NEWEST("001", "当前版本已是最新版本"),

    WENTIYUN_PUSH_FAILED("001", "微信推送失败"),
    WECHAT_PUSH_FAILED("001", "微信推送失败"),
    WEIBO_PUSH_FAILED("002", "微博发表失败"),
    WEIBO_TOKEN_FAILED("003", "授权码失效");

    String value;

    String text;

    ReturnCodeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
