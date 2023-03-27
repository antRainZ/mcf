package org.antrain.article.controller;

import java.util.List;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import org.antrain.article.domain.Article;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.antrain.common.annotation.Log;
import org.antrain.common.core.controller.BaseController;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.common.enums.BusinessType;
import org.antrain.article.vo.ArticleVo;
import org.antrain.article.bo.ArticleQueryBo;
import org.antrain.article.bo.ArticleAddBo;
import org.antrain.article.bo.ArticleEditBo;
import org.antrain.article.service.IArticleService;
import org.antrain.common.utils.poi.ExcelUtil;
import org.antrain.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文章Controller
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Api(value = "文章控制器", tags = {"文章管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/article/article")
public class ArticleController extends BaseController {

    private final IArticleService iArticleService;

    /**
     * 查询文章列表
     */
    @ApiOperation("查询文章列表")
    @PreAuthorize("@ss.hasPermi('article:article:list')")
    @GetMapping("/list")
    public TableDataInfo<ArticleVo> list(ArticleQueryBo bo) {
        startPage();
        List<ArticleVo> list = iArticleService.queryList(bo);
        return getDataTable(list);
    }

    /**
     * 导出文章列表
     */
    @ApiOperation("导出文章列表")
    @PreAuthorize("@ss.hasPermi('article:article:export')")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<ArticleVo> export(ArticleQueryBo bo) {
        List<ArticleVo> list = iArticleService.queryList(bo);
        ExcelUtil<ArticleVo> util = new ExcelUtil<ArticleVo>(ArticleVo.class);
        return util.exportExcel(list, "article" );
    }

    /**
     * 获取文章详细信息
     */
    @ApiOperation("获取文章详细信息")
    @PreAuthorize("@ss.hasPermi('article:article:query')")
    @GetMapping("/{id}")
    public AjaxResult<ArticleVo> getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iArticleService.queryById(id));
    }

    /**
     * 新增文章
     */
    @ApiOperation("新增文章")
    @PreAuthorize("@ss.hasPermi('article:article:add')")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@RequestBody ArticleAddBo bo) {
        bo.setUserId(1L);
        bo.setNickname("antrain");
        return toAjax(iArticleService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改文章
     */
    @ApiOperation("修改文章")
    @PreAuthorize("@ss.hasPermi('article:article:edit')")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@RequestBody ArticleEditBo bo) {
        return toAjax(iArticleService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 为文章分配标签
     */
    @PutMapping("/label")
    public AjaxResult editLabels(@RequestBody Article article) {
        iArticleService.updateById(article);
        return AjaxResult.success();
    }


    /**
     * 删除文章
     */
    @ApiOperation("删除文章")
    @PreAuthorize("@ss.hasPermi('article:article:remove')")
    @Log(title = "文章" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iArticleService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
