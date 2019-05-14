import {AppPage} from './app.po';
import {browser, logging} from 'protractor';

//基于protractor的端对端测试

//准备工作：
//需要预先安装好npm和angular
//执行命令行命令：
//npm install -g protractor
//webdriver-manager update --gecko false --alternate_cdn
//以上命令大概率会超时出错，请参考README.md中的问题解决方案配置镜像，或者自备vpn，然后重试
//如果出现其他错误，请使用命令webdriver-manager update --gecko false重试
//直至出现update-config.json为止

//仅对于Chrome 74
//执行命令行命令：
//ng build
//ng serve
//ng e2e

describe('workspace-project App', () => {
    let page: AppPage;

    beforeEach(() => {
        page = new AppPage();
    });

    it('should display welcome message', () => {
        page.navigateTo();
        expect(page.getTitleText()).toEqual('Welcome to frontend!');
    });

    afterEach(async () => {
        // Assert that there are no errors emitted from the browser
        const logs = await browser.manage().logs().get(logging.Type.BROWSER);
        expect(logs).not.toContain(jasmine.objectContaining({
            level: logging.Level.SEVERE,
        } as logging.Entry));
    });
});
