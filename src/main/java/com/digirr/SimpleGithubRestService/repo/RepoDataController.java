package com.digirr.SimpleGithubRestService.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/repositories/{owner}/{repository-name}")
public class RepoDataController {

    private final RepoDataService dataService;

    @Autowired
    public RepoDataController(RepoDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public Object getData(@PathVariable("owner") String owner,
                          @PathVariable("repository-name") String repo_name) {
        return dataService.getData(owner, repo_name);
    }

}
