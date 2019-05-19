export const environment = {
  production: true
};

/** 项目的后台api请求根地址。与后台内容根路径相对应。注意需要设置协议和端口。 */
export const apiUrl = "http://localhost:8080/csnt-portal/api";

/**加密过的项目的后台api请求根地址。*/
export const safeApiUrl = "https://localhost:8080/csnt-portal/api";

/**枚举值对应的文本。一级键名为枚举名，二级键名为枚举常量的值。*/
export const enumTexts = {
  DifficultyLevel: {
    EASY: "简单",
    NORMAL: "普通",
    HARD: "困难",
    HELL: "地狱",
    INHUMAN: "非人",
    IMMORTAL: "远非常人"
  },
  DynamicCategory: {
    CHAT: "闲聊",
    SEEK_HELP: "求助",
    DISCUSS: "讨论",
    NOTICE: "通知"
  },
  Gender: {
    MALE: "男性",
    FEMALE: "女性",
    ALIEN: "外星人"
  },
  LoginType: {
    BY_USERNAME: "用户名登录",
    BY_PHONE_NUM: "手机号码登录",
    BY_EMAIL: "邮箱地址登录"
  },
  Profession: {
    NONE: "无",
    CSNT: "计算机科学与技术",
    SE: "软件工程",
    IOT: "物联网工程",
    IMNIS: "信息管理与信息系统",
    DMT: "数字媒体技术"
  },
  ProfessionLevel: {
    NORMAL: "普通",
    PROFESSIONAL: "专业",
    VERY_PROFESSIONAL: "非常专业"
  },
  RoleText: {
    VISITOR: "游客",
    STUDENT: "学生",
    TEACHER: "教师",
    ADMIN: "管理员"
  }
};
