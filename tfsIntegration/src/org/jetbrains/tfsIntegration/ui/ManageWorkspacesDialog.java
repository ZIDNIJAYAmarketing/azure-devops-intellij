/*
 * Copyright 2000-2008 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.tfsIntegration.ui;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.VcsDirtyScopeManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ManageWorkspacesDialog extends DialogWrapper {
  private final Project myProject;

  public ManageWorkspacesDialog(final Project project) {
    super(project, true);
    myProject = project;
    setTitle("Manage TFS Servers and Workspaces");
    setOKButtonText("Close");

    init();
  }

  @NotNull
  protected Action[] createActions() {
    return new Action[]{getOKAction(), getHelpAction()};
  }


  @Nullable
  protected JComponent createCenterPanel() {
    ManageWorkspacesForm f = new ManageWorkspacesForm(myProject, true);
    f.setShowWorkspaces(true);
    return f.getContentPane();
  }

  @Override
  protected String getDimensionServiceKey() {
    return "TFS.ManageWorkspaces";
  }

  @Override
  protected String getHelpId() {
    return "project.propVCSSupport.VCSs.TFS.manage";
  }

  @Override
  protected void doOKAction() {
    super.doOKAction();
    VcsDirtyScopeManager.getInstance(myProject).markEverythingDirty();
  }
}
